package info.mapes.phasmophobiaapi.integration

import info.mapes.phasmophobiaapi.domains.Ghost
import io.micronaut.http.HttpRequest
import spock.lang.Unroll

import static info.mapes.phasmophobiaapi.domains.Ghost.*
import static info.mapes.phasmophobiaapi.domains.GhostEvidence.*

class EvidenceCalculationSpec extends AbstractIntegrationSpec {

    @Unroll("#inputEvidence results in #expectedGhosts")
    def "calculate options as expected"() {
        given: "a POST request with the collected evidence"
        def httpRequest = HttpRequest.POST("calculate_options", inputEvidence)

        expect: "the output body matches the expected result set"
        def response = v1HttpClient.toBlocking().retrieve(httpRequest)
        def mappedResponse = objectMapper.readValue(response, Ghost[].class).toList()

        and: "all returned ghosts match the expected response"
        expectedGhosts.containsAll(mappedResponse)

        where:
        inputEvidence                                         | expectedGhosts
//      base values for all ghosts
        [FREEZING_TEMPERATURES, EMF_LEVEL_FIVE, GHOST_ORBS]   | [Phantom]
        [FREEZING_TEMPERATURES, EMF_LEVEL_FIVE, FINGERPRINTS] | [Banshee]
        [FREEZING_TEMPERATURES, GHOST_ORBS, SPIRIT_BOX]       | [Mare]
        [FREEZING_TEMPERATURES, GHOST_ORBS, GHOST_WRITING]    | [Yurei]
        [FREEZING_TEMPERATURES, SPIRIT_BOX, GHOST_WRITING]    | [Demon]
        [FREEZING_TEMPERATURES, SPIRIT_BOX, FINGERPRINTS]     | [Wraith]
        [EMF_LEVEL_FIVE, GHOST_ORBS, SPIRIT_BOX]              | [Jinn]
        [EMF_LEVEL_FIVE, GHOST_ORBS, GHOST_WRITING]           | [Shade]
        [EMF_LEVEL_FIVE, SPIRIT_BOX, GHOST_WRITING]           | [Oni]
        [EMF_LEVEL_FIVE, GHOST_WRITING, FINGERPRINTS]         | [Revenant]
        [GHOST_ORBS, SPIRIT_BOX, FINGERPRINTS]                | [Poltergeist]
        [SPIRIT_BOX, GHOST_WRITING, FINGERPRINTS]             | [Spirit]
//      single evidence
        [FREEZING_TEMPERATURES]                               | [Phantom, Banshee, Mare, Yurei, Demon, Wraith]
        [EMF_LEVEL_FIVE]                                      | [Phantom, Banshee, Jinn, Shade, Oni, Revenant]
        [GHOST_ORBS]                                          | [Phantom, Mare, Yurei, Jinn, Shade, Poltergeist]
        [SPIRIT_BOX]                                          | [Mare, Demon, Wraith, Jinn, Oni, Poltergeist, Spirit]
        [GHOST_WRITING]                                       | [Yurei, Demon, Shade, Oni, Revenant, Spirit]
        [FINGERPRINTS]                                        | [Banshee, Wraith, Revenant, Poltergeist, Spirit]
//      double evidence
        [FREEZING_TEMPERATURES, EMF_LEVEL_FIVE]               | [Phantom, Banshee]
        [FREEZING_TEMPERATURES, GHOST_ORBS]                   | [Phantom, Mare, Yurei]
        [FREEZING_TEMPERATURES, SPIRIT_BOX]                   | [Mare, Demon, Wraith]
        [FREEZING_TEMPERATURES, GHOST_WRITING]                | [Yurei, Demon]
        [FREEZING_TEMPERATURES, FINGERPRINTS]                 | [Banshee, Wraith]
        [EMF_LEVEL_FIVE, GHOST_ORBS]                          | [Phantom, Jinn, Shade]
        [EMF_LEVEL_FIVE, SPIRIT_BOX]                          | [Jinn, Oni]
        [EMF_LEVEL_FIVE, GHOST_WRITING]                       | [Shade, Oni, Revenant]
        [EMF_LEVEL_FIVE, FINGERPRINTS]                        | [Banshee, Revenant]
        [GHOST_ORBS, SPIRIT_BOX]                              | [Mare, Jinn, Poltergeist]
        [GHOST_ORBS, GHOST_WRITING]                           | [Shade, Yurei]
        [GHOST_ORBS, FINGERPRINTS]                            | [Poltergeist]
        [SPIRIT_BOX, GHOST_WRITING]                           | [Demon, Oni, Spirit]
        [SPIRIT_BOX, FINGERPRINTS]                            | [Wraith, Poltergeist, Spirit]
        [GHOST_WRITING, FINGERPRINTS]                         | [Revenant, Spirit]
    }
}
