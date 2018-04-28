/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.parse

import org.jetbrains.kotlin.com.intellij.lang.PsiBuilder
import org.jetbrains.kotlin.com.intellij.lang.parser.GeneratedParserUtilBase
import org.jetbrains.kotlin.com.intellij.psi.TokenType
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType

object TomlParserUtil : GeneratedParserUtilBase() {
	@Suppress("UNUSED_PARAMETER")
	@JvmStatic
	fun remap(b: PsiBuilder, level: Int, from: IElementType, to: IElementType): Boolean {
		if (b.tokenType == from) {
			b.remapCurrentToken(to)
			b.advanceLexer()
			return true
		}
		return false
	}

	@JvmStatic
	fun atSameLine(b: PsiBuilder, level: Int, parser: Parser): Boolean {
		val marker = enter_section_(b)
		addVariant(b, "VALUE")
		b.tokenType // skip whitespace
		val result = !isNextAfterNewLine(b) && parser.parse(b, level)
		exit_section_(b, marker, null, result)
		return result
	}

	private fun isNextAfterNewLine(b: PsiBuilder) =
			b.rawLookup(-1) == TokenType.WHITE_SPACE && b.rawLookupText(-1).contains("\n")

	private fun PsiBuilder.rawLookupText(steps: Int): CharSequence {
		val start = rawTokenTypeStart(steps)
		val end = rawTokenTypeStart(steps + 1)
		return if (start == -1 || end == -1) "" else originalText.subSequence(start, end)
	}
}
