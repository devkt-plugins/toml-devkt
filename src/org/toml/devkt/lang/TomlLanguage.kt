/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang

import org.jetbrains.kotlin.com.intellij.lang.Language
import org.toml.devkt.lang.psi.TomlFileType

object TomlLanguage : Language("TOML", "text/toml") {
	override fun isCaseSensitive() = true
	override fun getAssociatedFileType() = TomlFileType
}
