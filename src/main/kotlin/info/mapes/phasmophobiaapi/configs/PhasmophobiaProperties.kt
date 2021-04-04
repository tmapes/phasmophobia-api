package info.mapes.phasmophobiaapi.configs

import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Context
import javax.inject.Singleton

@Context
@Singleton
@ConfigurationProperties("phasmophobia")
data class PhasmophobiaProperties @ConfigurationInject constructor(
    val evidenceTypes: List<Evidence>,
    val ghostTypes: List<Ghost>,
)

data class Evidence(
    val name: String,
    val code: String,
)

data class Ghost(
    val name: String,
    val code: String,
    private val evidenceCodes: List<String>,
) {
    val evidenceTypes: List<Evidence> = emptyList()
}
