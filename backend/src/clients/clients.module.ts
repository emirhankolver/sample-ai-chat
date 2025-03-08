import { Module } from '@nestjs/common';
import { OpenAIClient } from './open-ai.client';

@Module({
  providers: [OpenAIClient],
  exports: [OpenAIClient],
})
export class ClientsModule {

}