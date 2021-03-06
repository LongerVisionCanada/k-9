package com.fsck.k9.autodiscovery.thunderbird

import com.fsck.k9.autodiscovery.ConnectionSettingsDiscovery
import com.fsck.k9.autodiscovery.DiscoveryResults
import com.fsck.k9.autodiscovery.DiscoveryTarget

class ThunderbirdDiscovery(
    private val fetcher: ThunderbirdAutoconfigFetcher,
    private val parser: ThunderbirdAutoconfigParser
) : ConnectionSettingsDiscovery {

    override fun discover(email: String, target: DiscoveryTarget): DiscoveryResults? {
        val autoconfigInputStream = fetcher.fetchAutoconfigFile(email) ?: return null

        return autoconfigInputStream.use {
            parser.parseSettings(it, email)
        }
    }

    override fun toString(): String = "Thunderbird autoconfig"
}
