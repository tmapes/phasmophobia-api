package info.mapes.phasmophobiaapi.integration

import io.micronaut.http.HttpRequest

class GetAllGhostsSpec extends AbstractIntegrationSpec {

    def "getAllGhostsSuccessfully"() {
        given: "a GET request to the all ghosts endpoint"
        def httpRequest = HttpRequest.GET("/")

        when: "a get call is made for all ghosts"
        List<Object> rawResponse = v1HttpClient.toBlocking().retrieve(httpRequest, List.class)

        then: "The list looks as expected"
        rawResponse == [[ghost_type: 'SPIRIT', evidence_options: ['SPIRIT_BOX', 'GHOST_WRITING', 'FINGERPRINTS']], [ghost_type: 'POLTERGEIST', evidence_options: ['GHOST_ORBS', 'SPIRIT_BOX', 'FINGERPRINTS']], [ghost_type: 'ONI', evidence_options: ['EMF_LEVEL_FIVE', 'SPIRIT_BOX', 'GHOST_WRITING']], [ghost_type: 'SHADE', evidence_options: ['EMF_LEVEL_FIVE', 'GHOST_ORBS', 'GHOST_WRITING']], [ghost_type: 'JINN', evidence_options: ['EMF_LEVEL_FIVE', 'GHOST_ORBS', 'SPIRIT_BOX']], [ghost_type: 'WRAITH', evidence_options: ['FREEZING_TEMPERATURES', 'SPIRIT_BOX', 'FINGERPRINTS']], [ghost_type: 'DEMON', evidence_options: ['FREEZING_TEMPERATURES', 'SPIRIT_BOX', 'GHOST_WRITING']], [ghost_type: 'YUREI', evidence_options: ['FREEZING_TEMPERATURES', 'GHOST_ORBS', 'GHOST_WRITING']], [ghost_type: 'MARE', evidence_options: ['FREEZING_TEMPERATURES', 'GHOST_ORBS', 'SPIRIT_BOX']], [ghost_type: 'BANSHEE', evidence_options: ['FREEZING_TEMPERATURES', 'EMF_LEVEL_FIVE', 'FINGERPRINTS']], [ghost_type: 'PHANTOM', evidence_options: ['FREEZING_TEMPERATURES', 'EMF_LEVEL_FIVE', 'GHOST_ORBS']], [ghost_type: 'REVENANT', evidence_options: ['EMF_LEVEL_FIVE', 'GHOST_WRITING', 'FINGERPRINTS']]]

    }
}
