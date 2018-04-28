/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.psi.impl

import org.jetbrains.kotlin.com.intellij.extapi.psi.ASTWrapperPsiElement
import org.jetbrains.kotlin.com.intellij.lang.ASTNode
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.util.PsiTreeUtil
import org.toml.devkt.lang.psi.*
import org.toml.devkt.lang.psi.TomlElementTypes.*

class TomlKeyValueImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlKeyValue {
	override val key: TomlKey get() = childOfTypeNotNull()
	override val value: TomlValue? get() = childOfTypeNullable()
	override fun toString(): String = "TomlKeyValue"
}

class TomlKeyImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlKey {
	override fun toString(): String = "TomlKey"
}

class TomlLiteralImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlLiteral {
	override fun toString(): String = "TomlLiteral"
}

class TomlArrayImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlArray {
	override val elements: List<TomlValue> get() = childrenOfType()
	override fun toString(): String = "TomlArray"
}

class TomlTableImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlTable {
	override val header: TomlTableHeader get() = childOfTypeNotNull()
	override val entries: List<TomlKeyValue> get() = childrenOfType()
	override fun toString(): String = "TomlTable"
}

class TomlTableHeaderImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlTableHeader {
	override val names: List<TomlKey> get() = childrenOfType()
	override fun toString(): String = "TomlTableHeader"
}

class TomlInlineTableImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlInlineTable {
	override val entries: List<TomlKeyValue> get() = childrenOfType()

	override fun toString(): String = "TomlInlineTable"
}

class TomlArrayTableImpl(node: ASTNode) : ASTWrapperPsiElement(node), TomlArrayTable {
	override val header: TomlTableHeader get() = childOfTypeNotNull()
	override val entries: List<TomlKeyValue> get() = childrenOfType()
	override fun toString(): String = "TomlArrayTable"
}

object TomlASTFactory {
	fun createComposite(node: ASTNode): PsiElement = when (node.elementType) {
		KEY_VALUE -> TomlKeyValueImpl(node)
		KEY -> TomlKeyImpl(node)
		LITERAL -> TomlLiteralImpl(node)
		ARRAY -> TomlArrayImpl(node)
		TABLE -> TomlTableImpl(node)
		TABLE_HEADER -> TomlTableHeaderImpl(node)
		INLINE_TABLE -> TomlInlineTableImpl(node)
		ARRAY_TABLE -> TomlArrayTableImpl(node)
		else -> error("Unknown TOML element type: `$node`")
	}
}

private inline fun <reified T : TomlElement> ASTWrapperPsiElement.childOfTypeNullable(): T? =
		PsiTreeUtil.getChildOfType(this, T::class.java)

private inline fun <reified T : TomlElement> ASTWrapperPsiElement.childOfTypeNotNull(): T =
		PsiTreeUtil.getChildOfType(this, T::class.java)
				?: error("""
            Invalid TOML PSI
            Expected to find `${T::class.simpleName}` child of ${this::class.simpleName}
            Element text:
        """.trimIndent() + "\n$text"
				)

private inline fun <reified T : TomlElement> ASTWrapperPsiElement.childrenOfType(): List<T> =
		PsiTreeUtil.getChildrenOfTypeAsList(this, T::class.java)
