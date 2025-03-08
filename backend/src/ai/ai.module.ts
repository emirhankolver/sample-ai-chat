import { Module } from '@nestjs/common';
import AiController from './ai.controller';
import { AiService } from './ai.service';
import { ClientsModule } from '../clients/clients.module';

@Module({
  imports: [ClientsModule],
  controllers: [AiController],
  providers: [AiService],
})
export class AiModule {
}
