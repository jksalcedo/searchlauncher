package com.searchlauncher.app.data

sealed class CustomShortcut {
        abstract val description: String
        abstract val packageName: String?

        data class Search(
                val trigger: String,
                val urlTemplate: String,
                override val description: String,
                override val packageName: String? = null
        ) : CustomShortcut()

        data class Action(
                val intentUri: String,
                override val description: String,
                override val packageName: String? = null
        ) : CustomShortcut()
}

object CustomShortcuts {
        private val settingsActions =
                listOf(
                        "android.settings.ACCESSIBILITY_SETTINGS",
                        "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS",
                        "android.settings.ADD_ACCOUNT_SETTINGS",
                        "android.settings.AIRPLANE_MODE_SETTINGS",
                        "android.settings.APN_SETTINGS",
                        "android.settings.APPLICATION_DETAILS_SETTINGS",
                        "android.settings.APPLICATION_DEVELOPMENT_SETTINGS",
                        "android.settings.APPLICATION_SETTINGS",
                        "android.settings.APP_NOTIFICATION_SETTINGS",
                        "android.settings.BATTERY_SAVER_SETTINGS",
                        "android.settings.BLUETOOTH_SETTINGS",
                        "android.settings.CAPTIONING_SETTINGS",
                        "android.settings.CAST_SETTINGS",
                        "android.settings.CHANNEL_NOTIFICATION_SETTINGS",
                        "android.settings.DATA_ROAMING_SETTINGS",
                        "android.settings.DATA_USAGE_SETTINGS",
                        "android.settings.DATE_SETTINGS",
                        "android.settings.DEVICE_INFO_SETTINGS",
                        "android.settings.DISPLAY_SETTINGS",
                        "android.settings.DREAM_SETTINGS",
                        "android.settings.ENTERPRISE_PRIVACY_SETTINGS",
                        "android.settings.FINGERPRINT_ENROLL",
                        "android.settings.HARD_KEYBOARD_SETTINGS",
                        "android.settings.HOME_SETTINGS",
                        "android.settings.IGNORE_BACKGROUND_DATA_RESTRICTIONS_SETTINGS",
                        "android.settings.IGNORE_BATTERY_OPTIMIZATION_SETTINGS",
                        "android.settings.INPUT_METHOD_SETTINGS",
                        "android.settings.INPUT_METHOD_SUBTYPE_SETTINGS",
                        "android.settings.INTERNAL_STORAGE_SETTINGS",
                        "android.settings.LOCALE_SETTINGS",
                        "android.settings.LOCATION_SOURCE_SETTINGS",
                        "android.settings.MANAGE_ALL_APPLICATIONS_SETTINGS",
                        "android.settings.MANAGE_APPLICATIONS_SETTINGS",
                        "android.settings.MANAGE_DEFAULT_APPS_SETTINGS",
                        "android.settings.MANAGE_UNKNOWN_APP_SOURCES",
                        "android.settings.MEMORY_CARD_SETTINGS",
                        "android.settings.NETWORK_OPERATOR_SETTINGS",
                        "android.settings.NFCSHARING_SETTINGS",
                        "android.settings.NFC_PAYMENT_SETTINGS",
                        "android.settings.NFC_SETTINGS",
                        "android.settings.NIGHT_DISPLAY_SETTINGS",
                        "android.settings.NOTIFICATION_POLICY_ACCESS_SETTINGS",
                        "android.settings.PRIVACY_SETTINGS",
                        "android.settings.REQUEST_IGNORE_BATTERY_OPTIMIZATIONS",
                        "android.settings.REQUEST_SET_AUTOFILL_SERVICE",
                        "android.settings.SECURITY_SETTINGS",
                        "android.settings.SETTINGS",
                        "android.settings.SHOW_REGULATORY_INFO",
                        "android.settings.SOUND_SETTINGS",
                        "android.settings.STORAGE_VOLUME_ACCESS_SETTINGS",
                        "android.settings.SYNC_SETTINGS",
                        "android.settings.USAGE_ACCESS_SETTINGS",
                        "android.settings.VPN_SETTINGS",
                        "android.settings.VR_LISTENER_SETTINGS",
                        "android.settings.WEBVIEW_SETTINGS",
                        "android.settings.WIFI_SETTINGS",
                        "android.settings.WIRELESS_SETTINGS",
                        "android.settings.ZEN_MODE_PRIORITY_SETTINGS",
                        "android.settings.action.MANAGE_OVERLAY_PERMISSION",
                        "android.settings.action.MANAGE_WRITE_SETTINGS"
                )

        private fun generateSettingsShortcuts(): List<CustomShortcut.Action> {
                return settingsActions.map { action ->
                        val name =
                                action.substringAfterLast(".")
                                        .replace("_", " ")
                                        .lowercase()
                                        .split(" ")
                                        .joinToString(" ") {
                                                it.replaceFirstChar { char -> char.uppercase() }
                                        }
                                        .replace("Settings", "")
                                        .trim() + " Settings"

                        CustomShortcut.Action(
                                intentUri = "intent:#Intent;action=$action;end",
                                description = name
                        )
                }
        }

        val shortcuts =
                listOf(
                        CustomShortcut.Search(
                                "g",
                                "https://www.google.com/search?q=%s",
                                "Google Search"
                        ),
                        CustomShortcut.Search("call", "tel:%s", "Call"),
                        CustomShortcut.Search("sms", "sms:%s", "Send SMS"),
                        CustomShortcut.Search("mailto", "mailto:%s", "Send Email to"),
                        CustomShortcut.Search("gmail", "gmail://search/%s", "Gmail Search"),
                        CustomShortcut.Search(
                                "cal",
                                "intent:#Intent;action=android.intent.action.INSERT;type=vnd.android.cursor.item/event;S.title=%s;end",
                                "Add Calendar Item"
                        ),
                        CustomShortcut.Search(
                                "ff",
                                "https://www.google.com/search?q=%s",
                                "Firefox Search",
                                "org.mozilla.firefox"
                        ),
                        CustomShortcut.Search(
                                "yt",
                                "https://www.youtube.com/results?search_query=%s",
                                "YouTube Search"
                        ),
                        CustomShortcut.Search("nav", "geo:0,0?q=%s", "Navigate to"),
                        CustomShortcut.Search(
                                "maps",
                                "https://www.google.com/maps/search/%s",
                                "Google Maps Search"
                        ),
                        CustomShortcut.Search(
                                "r",
                                "https://www.reddit.com/search/?q=%s",
                                "Reddit Search"
                        ),
                        CustomShortcut.Search(
                                "w",
                                "https://en.wikipedia.org/w/index.php?search=%s",
                                "Wikipedia Search"
                        ),
                        CustomShortcut.Search("gpt", "https://chatgpt.com/?q=%s", "ChatGPT"),
                        CustomShortcut.Search(
                                "s",
                                "spotify:search:%s",
                                "Spotify Search",
                                "com.spotify.music"
                        )
                ) + generateSettingsShortcuts()
}
