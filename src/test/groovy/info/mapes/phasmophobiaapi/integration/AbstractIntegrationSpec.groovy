package info.mapes.phasmophobiaapi.integration

import com.fasterxml.jackson.databind.ObjectMapper
import info.mapes.phasmophobiaapi.ApplicationKt
import io.micronaut.http.client.RxHttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest(environments = ["test"], application = ApplicationKt)
abstract class AbstractIntegrationSpec extends Specification {

    @Inject
    @Client("/phasmophobia_ghosts/v1")
    @Shared
    protected RxHttpClient v1HttpClient

    @Inject
    @Shared
    protected ObjectMapper objectMapper
}
