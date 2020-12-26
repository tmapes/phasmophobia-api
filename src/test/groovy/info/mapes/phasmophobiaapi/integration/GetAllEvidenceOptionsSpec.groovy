package info.mapes.phasmophobiaapi.integration

import io.micronaut.http.HttpRequest

class GetAllEvidenceOptionsSpec extends AbstractIntegrationSpec {

    def "getAllGhostsSuccessfully"() {
        given: "a GET request to the all ghosts endpoint"
        def httpRequest = HttpRequest.GET("/evidence_options")

        when: "a get call is made for all evidence options"
        List<String> rawResponse = v1HttpClient.toBlocking().retrieve(httpRequest, List.class)

        then: "The list looks as expected"
        rawResponse == [
                "SPIRIT_BOX",
                "FINGERPRINTS",
                "GHOST_WRITING",
                "FREEZING_TEMPERATURES",
                "EMF_LEVEL_FIVE",
                "GHOST_ORBS"
        ]

        0 * _
    }
}
