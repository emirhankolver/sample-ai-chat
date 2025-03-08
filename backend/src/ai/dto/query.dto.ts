import { ApiProperty } from '@nestjs/swagger';
import { MessageDto } from './message.dto';

export class QueryDto {
  // Swagger için gereklidir.
  @ApiProperty()
  readonly prompt: string;
  @ApiProperty()
  readonly messageHistory: MessageDto[];
}
