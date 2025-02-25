package org.entdes.todolist;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.entdes.mail.IEmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * Classe de test per verificar el comportament del GestorTasques
 * utilitzant Mockito per simular l'enviament d'emails.
 */
@ExtendWith(MockitoExtension.class)
class GestorTasquesMockTest {

    @Mock
    private IEmailService emailService; // Simulació del servei d'enviament de correus

    private GestorTasques gestor;
    private final String recipient = "test@example.com";

    /**
     * Inicialitza el GestorTasques abans de cada test.
     */
    @BeforeEach
    void setUp() {
        gestor = new GestorTasques(emailService, recipient);
    }

    /**
     * Comprova que quan s'afegeix una tasca, s'envia un correu de notificació.
     */
    @Test
    void testAfegirTascaEnviaEmail() throws Exception {
        String desc = "Test tasca email";
        LocalDate dataInici = LocalDate.now().plusDays(1);
        
        gestor.afegirTasca(desc, dataInici, null, 2);
        
        // Verifica que l'email s'ha enviat exactament una vegada amb el contingut correcte
        verify(emailService, times(1))
            .enviarCorreu(recipient, "Nova Tasca Creada", "Has creat la tasca: " + desc);
    }

    /**
     * Comprova que si es passa una descripció buida, es genera una excepció
     * i no s'envia cap correu.
     */
    @Test
    void testAfegirTascaAmbDescripcioBuidaNoEnviaEmail() throws Exception {
        assertThrows(Exception.class, () -> {
            gestor.afegirTasca("   ", null, null, null);
        });
        
        // Verifica que no s'ha enviat cap correu
        verify(emailService, times(0))
            .enviarCorreu(anyString(), anyString(), anyString());
    }
    
    /**
     * Comprova el comportament de l'enviament de correus utilitzant un stub
     * en lloc de Mockito.
     */
    @Test
    void testAfegirTascaEnviaEmailAmbStub() throws Exception {
        EmailServiceStub stubService = new EmailServiceStub();
        GestorTasques gestorStub = new GestorTasques(stubService, recipient);
        String desc = "Tasca amb stub";
        LocalDate dataInici = LocalDate.now().plusDays(1);
        
        gestorStub.afegirTasca(desc, dataInici, null, 3);
        
        // Verifica que el stub ha registrat la crida correctament
        assert stubService.enviarCalled;
        assert stubService.destinatari.equals(recipient);
        assert stubService.subject.equals("Nova Tasca Creada");
        assert stubService.message.contains(desc);
    }

    /**
     * Implementació d'un stub del servei d'email per verificar crides
     * sense utilitzar Mockito.
     */
    private static class EmailServiceStub implements IEmailService {
        boolean enviarCalled = false;
        String destinatari;
        String subject;
        String message;
        
        @Override
        public void enviarCorreu(String destinatari, String subject, String message) {
            this.enviarCalled = true;
            this.destinatari = destinatari;
            this.subject = subject;
            this.message = message;
        }
    }
}