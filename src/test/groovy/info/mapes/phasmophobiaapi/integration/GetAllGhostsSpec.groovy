package info.mapes.phasmophobiaapi.integration

import info.mapes.phasmophobiaapi.domains.Ghost
import io.micronaut.http.HttpRequest

class GetAllGhostsSpec extends AbstractIntegrationSpec {

    def "getAllGhostsSuccessfully"() {
        given: "a GET request to the all ghosts endpoint"
        def httpRequest = HttpRequest.GET("/")

        when: "a get call is made for all ghosts"
        def response = objectMapper.readValue(v1HttpClient.toBlocking().retrieve(httpRequest), Ghost[].class).toList()

        then: "The list looks as expected"
        Ghost.ALL_GHOSTS == response

        0 * _
    }
}
