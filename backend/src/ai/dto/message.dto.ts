import { ApiProperty } from '@nestjs/swagger';

export class MessageDto {
  @ApiProperty()
  readonly message: string;
  @ApiProperty()
  readonly isUserCreated: boolean;
}