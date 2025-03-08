import { Body, Controller, Post, Res } from '@nestjs/common';
import { AiService } from './ai.service';
import { AIResponse } from './interfaces/ai-response.interface';
import { QueryDto } from './dto/query.dto';
import { Response } from 'express';

@Controller('api/v1/ai')
export default class AiController {
  constructor(private readonly aiService: AiService) {
  }

  // @Body() Swagger için gereklidir.
  @Post('query')
  async query(@Body() queryDto: QueryDto, @Res() res: Response): Promise<void> {
    return this.aiService.processQuery(queryDto, res);
  }
}
