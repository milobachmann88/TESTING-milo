package etec.backend.controller

import io.kotest.core.spec.style.BehaviorSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest // 1. Arranca el contexto completo de Spring Boot (Servicios reales, etc.)
@AutoConfigureMockMvc // 2. Configura e inyecta el cliente MockMvc para llamadas HTTP
class CalculadoraDeDescuentosIT : BehaviorSpec({

    // 3. Declaramos navegador como propiedad inyectada para respetar el constructor sin argumentos de Kotest
    @Autowired
    lateinit var navegador: MockMvc

    init {
        Given("la aplicación cargada completamente con sus servicios reales") {

            And("un usuario '123' configurado en el sistema con 25% de descuento") {
                // NOTA: Usa la lógica real del servicio CalculadoraDeDescuentosService

                When("se hace una consulta GET /api/descuentos?userID=123&monto=100.0") {
                    val respuesta =
                        navegador.perform(
                            get("/api/descuentos")
                                .param("userID", "123")
                                .param("monto", "100.0")
                        )

                    Then("deberia obtener respuesta con estado ok") {
                        respuesta.andExpect(status().isOk)
                    }

                    And("el monto final devuelto por la lógica real debe ser de 75.0") {
                        respuesta.andExpect(jsonPath("$.montoFinal").value(75.0))
                    }
                }
            }
        }
    }
})