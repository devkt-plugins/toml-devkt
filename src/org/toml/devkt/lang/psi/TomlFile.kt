/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.psi

import org.jetbrains.kotlin.com.intellij.extapi.psi.PsiFileBase
import org.jetbrains.kotlin.com.intellij.openapi.fileTypes.FileType
import org.jetbrains.kotlin.com.intellij.psi.FileViewProvider
import org.toml.devkt.lang.TomlLanguage

class TomlFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, TomlLanguage) {
	override fun getFileType(): FileType = TomlFileType
	override fun toString(): String = "TOML File"
}
