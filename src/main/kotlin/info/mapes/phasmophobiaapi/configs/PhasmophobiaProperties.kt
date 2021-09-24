package info.mapes.phasmophobiaapi.configs

import info.mapes.phasmophobiaapi.utils.mapToSet
import io.micronaut.context.annotation.ConfigurationInject
import io.micronaut.context.annotation.ConfigurationProperties
import io.micronaut.context.annotation.Context
import java.lang.IllegalArgumentException
import javax.inject.Singleton

@Context
@Singleton
@ConfigurationProperties("phasmophobia")
data class PhasmophobiaProperties @ConfigurationInject constructor(
    val evidenceTypes: Set<Evidence>,
    private val ghostTypes: Set<Ghost>,
) {
    val evidenceMap: Map<String, Evidence> = evidenceTypes.associateBy { it.code }

    val ghosts: List<Ghost> = ghostTypes.map {
        val evidence = it.evidenceCodes.mapToSet { e ->
            evidenceMap[e] ?: throw IllegalArgumentException("$e is not a valid evidence type")
        }
        it.copy(evidenceTypes = evidence)
    }
}

data class Evidence(
    val name: String,
    val code: String,
)

data class Ghost(
    val name: String,
    val code: String,
    val evidenceCodes: Set<String>,
    val evidenceTypes: Set<Evidence> = emptySet()
)
