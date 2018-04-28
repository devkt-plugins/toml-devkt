/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.psi

import org.ice1000.devkt.openapi.ui.IconLoader
import org.jetbrains.kotlin.com.intellij.openapi.fileTypes.LanguageFileType
import org.jetbrains.kotlin.com.intellij.openapi.vfs.VirtualFile
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType
import org.toml.devkt.lang.TomlLanguage

class TomlTokenType(debugName: String) : IElementType(debugName, TomlLanguage)
class TomlCompositeType(debugName: String) : IElementType(debugName, TomlLanguage)

object TomlFileType : LanguageFileType(TomlLanguage) {
	override fun getName() = "TOML file"
	override fun getDescription() = "TOML file"
	override fun getDefaultExtension() = "toml"
	override fun getCharset(file: VirtualFile, content: ByteArray) = "UTF-8"
	private val icon = IconLoader.getIcon("/icon/cargo.png")
	override fun getIcon() = icon
}
