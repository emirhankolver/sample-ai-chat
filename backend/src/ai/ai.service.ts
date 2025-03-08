import { QueryDto } from './dto/query.dto';
import { OpenAIClient } from '../clients/open-ai.client';
import { Injectable, Res } from '@nestjs/common';
import { Response } from 'express';

@Injectable()
export class AiService {
  constructor(private readonly openAiClient: OpenAIClient) {}

  async processQuery(queryDto: QueryDto, @Res() res: Response) {
    res.setHeader('Content-Type', 'text/plain'); // Set response type
    res.setHeader('Transfer-Encoding', 'chunked'); // Enable streaming
    console.log("Handling query!", queryDto.prompt);

    const stream = await this.openAiClient.query(queryDto);
    stream.pipe(res); // Pipe tokens directly to response
  }
}
