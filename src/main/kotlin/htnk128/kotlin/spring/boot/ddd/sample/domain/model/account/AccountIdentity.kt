package htnk128.kotlin.spring.boot.ddd.sample.domain.model.account

import htnk128.kotlin.spring.boot.ddd.sample.domain.model.Identity
import java.util.UUID

class AccountIdentity(override val value: String) : Identity<AccountIdentity, String> {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as AccountIdentity
        return sameValueAs(other)
    }

    override fun hashCode(): Int = value.hashCode()

    override fun toString(): String = value

    override fun sameValueAs(other: AccountIdentity): Boolean = value == other.value

    companion object {

        fun generate(): AccountIdentity = AccountIdentity(UUID.randomUUID().toString())

        private val LENGTH_RANGE = (1..64)
        private val PATTERN = "[\\p{Alnum}-_]*".toRegex()

        fun valueOf(value: String): AccountIdentity {
            return value
                .takeIf { LENGTH_RANGE.contains(it.length) && PATTERN.matches(it) }
                ?.let { AccountIdentity(it) }
                ?: throw IllegalArgumentException("accountId must be 64 characters or less and alphanumeric.")
        }
    }
}