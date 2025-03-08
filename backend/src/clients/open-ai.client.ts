import { Injectable } from '@nestjs/common';
import OpenAI from 'openai';
import { PassThrough } from 'stream';
import { QueryDto } from '../ai/dto/query.dto';

@Injectable()
export class OpenAIClient {
  private readonly client: OpenAI;

  constructor() {
    this.client = new OpenAI({
      baseURL: process.env.OPEN_AI_BASE_URL,
      apiKey: process.env.OPEN_AI_API_KEY,
    });
  }

  async query(query: QueryDto): Promise<PassThrough> {
    const messages: OpenAI.Chat.Completions.ChatCompletionMessageParam[] = [
      { role: 'system', content: 'You are a helpful assistant named SampleAI. Don\'t exceed 250 tokens for each message.' },
      ...(query.messageHistory.map((res) => ({
        role: res.isUserCreated ? 'user': 'assistant',
        content: res.message,
      } as OpenAI.Chat.Completions.ChatCompletionMessageParam))),
      { role: 'user', content: query.prompt },
    ];

    const stream = await this.client.chat.completions.create({
      messages: messages,
      model: 'deepseek-chat',
      stream: true, // Enable streaming
    });

    const passThrough = new PassThrough();

    (async () => {
      for await (const chunk of stream) {
        const token = chunk.choices[0]?.delta?.content || '';
        console.log(token);
        passThrough.write(token); // Write tokens into stream
      }
      passThrough.end(); // End stream after completion
    })();

    return passThrough;
  }
}
