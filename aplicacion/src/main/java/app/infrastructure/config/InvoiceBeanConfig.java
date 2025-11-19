package app.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import app.application.usecases.CreateInvoiceUseCase;
import app.domain.ports.InvoicePort;

@Configuration
public class InvoiceBeanConfig {

    @Bean
    public CreateInvoiceUseCase createInvoiceUseCase(InvoicePort invoicePort) {
        return new CreateInvoiceUseCase(invoicePort);
    }
}
