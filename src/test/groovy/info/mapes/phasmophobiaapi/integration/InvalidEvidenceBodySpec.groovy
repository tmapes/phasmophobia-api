package info.mapes.phasmophobiaapi.integration

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.exceptions.HttpClientResponseException

class InvalidEvidenceBodySpec extends AbstractIntegrationSpec {

    def "Empty list of evidence is a 400"() {
        given: "an empty list of evidence"
        List<String> foundEvidence = []

        and: "a POST request"
        HttpRequest<List<String>> httpRequest = HttpRequest.POST("calculate_options", foundEvidence)

        when: "the post request is executed"
        v1HttpClient.toBlocking().exchange(httpRequest)

        then: "the httpStatusException is caught"
        HttpClientResponseException caught = thrown(HttpClientResponseException.class)
        HttpStatus.BAD_REQUEST == caught.status
        "foundEvidence: size must be between 1 and 3" == caught.message

        0 * _
    }

    def "list of 4 evidence is a 400"() {
        given: "an empty list of evidence"
        List<String> foundEvidence = ["ONE", "TWO", "THREE", "FOUR"]

        and: "a POST request"
        HttpRequest<List<String>> httpRequest = HttpRequest.POST("calculate_options", foundEvidence)

        when: "the post request is executed"
        v1HttpClient.toBlocking().exchange(httpRequest)

        then: "the httpStatusException is caught"
        HttpClientResponseException caught = thrown(HttpClientResponseException.class)
        HttpStatus.BAD_REQUEST == caught.status
        "foundEvidence: size must be between 1 and 3" == caught.message

        0 * _
    }
}
