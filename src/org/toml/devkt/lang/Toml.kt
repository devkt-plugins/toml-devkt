package org.toml.devkt.lang

import org.ice1000.devkt.ASTToken
import org.ice1000.devkt.openapi.*
import org.ice1000.devkt.ui.DevKtIcons
import org.jetbrains.kotlin.com.intellij.psi.PsiElement
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType
import org.toml.devkt.lang.parse.TomlParserDefinition
import org.toml.devkt.lang.psi.TomlElementTypes
import org.toml.devkt.lang.psi.TomlKey
import javax.swing.Icon

class Toml<T> : ExtendedDevKtLanguage<T>(TomlLanguage, TomlParserDefinition) {
	override fun satisfies(fileName: String) = fileName == "config" || fileName.endsWith(".toml")
	override val lineCommentStart get() = "#"
	override val icon: Icon get() = DevKtIcons.ANY
	override fun attributesOf(type: IElementType, colorScheme: ColorScheme<T>) = when (type) {
		TomlElementTypes.BASIC_STRING,
		TomlElementTypes.MULTILINE_BASIC_STRING,
		TomlElementTypes.MULTILINE_LITERAL_STRING,
		TomlElementTypes.LITERAL_STRING -> colorScheme.string
		TomlElementTypes.COMMENT -> colorScheme.lineComments
		TomlElementTypes.COMMA -> colorScheme.comma
		TomlElementTypes.BOOLEAN -> colorScheme.keywords
		TomlElementTypes.NUMBER -> colorScheme.numbers
		TomlElementTypes.EQ -> colorScheme.operators
		TomlElementTypes.BARE_KEY -> colorScheme.identifiers
		TomlElementTypes.BARE_KEY_OR_DATE -> colorScheme.metaData
		TomlElementTypes.BARE_KEY_OR_NUMBER -> colorScheme.numbers
		TomlElementTypes.L_BRACKET,
		TomlElementTypes.R_BRACKET -> colorScheme.brackets
		TomlElementTypes.L_CURLY,
		TomlElementTypes.R_CURLY -> colorScheme.braces
		else -> super.attributesOf(type, colorScheme)
	}

	override fun invokeAutoPopup(currentElement: ASTToken, inputString: String): Boolean {
		return inputString.firstOrNull()?.let { it in "" } == true || super.invokeAutoPopup(currentElement, inputString)
	}

	override fun annotate(element: PsiElement, document: AnnotationHolder<T>, colorScheme: ColorScheme<T>) {
		super.annotate(element, document, colorScheme)
		if (element.nodeType == TomlElementTypes.NUMBER) document.highlight(element, colorScheme.numbers)
		else if (element is TomlKey) document.highlight(element, colorScheme.keywords)
	}
}
