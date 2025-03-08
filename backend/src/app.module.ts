import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AiModule } from './ai/ai.module';
import { ClientsModule } from './clients/clients.module';

@Module({
  imports: [AiModule, ClientsModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
