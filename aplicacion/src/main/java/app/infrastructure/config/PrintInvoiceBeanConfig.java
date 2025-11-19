package app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.application.usecases.PrintInvoiceUseCase;

@Configuration
public class PrintInvoiceBeanConfig {
    @Bean
    public PrintInvoiceUseCase printInvoiceUseCase() {
        return new PrintInvoiceUseCase();
    }
}
