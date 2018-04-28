/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.psi

import org.jetbrains.kotlin.com.intellij.psi.*

interface TomlElement : PsiElement

// region abstract base types

interface TomlValue : TomlElement

interface TomlKeyValueOwner : TomlElement {
	val entries: List<TomlKeyValue>
}

interface TomlHeaderOwner : TomlElement {
	val header: TomlTableHeader
}

// endregion

interface TomlKeyValue : TomlElement {
	val key: TomlKey
	val value: TomlValue?
}

/**
 * It's possible to use [PsiReferenceContributor] to inject references
 * into [TomlKey] from third-party plugins.
 */
interface TomlKey : TomlElement

/**
 * It's possible to use [PsiReferenceContributor] to inject references
 * into [TomlLiteral] from third-party plugins.
 */
interface TomlLiteral : TomlValue

interface TomlArray : TomlValue {
	val elements: List<TomlValue>
}

interface TomlTable : TomlKeyValueOwner, TomlHeaderOwner
interface TomlArrayTable : TomlKeyValueOwner, TomlHeaderOwner
interface TomlInlineTable : TomlKeyValueOwner, TomlValue

interface TomlTableHeader : TomlElement {
	val names: List<TomlKey>
}

