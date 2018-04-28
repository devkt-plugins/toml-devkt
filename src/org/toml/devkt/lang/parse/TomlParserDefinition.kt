/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.toml.devkt.lang.parse

import org.jetbrains.kotlin.com.intellij.lang.*
import org.jetbrains.kotlin.com.intellij.lexer.Lexer
import org.jetbrains.kotlin.com.intellij.openapi.project.Project
import org.jetbrains.kotlin.com.intellij.psi.*
import org.jetbrains.kotlin.com.intellij.psi.tree.IFileElementType
import org.jetbrains.kotlin.com.intellij.psi.tree.TokenSet
import org.toml.devkt.lang.TomlLanguage
import org.toml.devkt.lang.psi.TomlElementTypes
import org.toml.devkt.lang.psi.TomlFile
import org.toml.devkt.lang.psi.impl.TomlASTFactory

object TomlParserDefinition : ParserDefinition {
	override fun createParser(project: Project?): PsiParser = TomlParser()
	override fun createFile(viewProvider: FileViewProvider): PsiFile = TomlFile(viewProvider)
	override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements =
			LanguageUtil.canStickTokensTogetherByLexer(left, right, TomlLexer())

	override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
	override fun getCommentTokens(): TokenSet = COMMENTS
	override fun getFileNodeType(): IFileElementType? = FILE
	override fun createLexer(project: Project?): Lexer = TomlLexer()

	/** @see org.toml.devkt.lang.psi.impl.TomlASTFactory */
	override fun createElement(node: ASTNode): PsiElement =
			TomlASTFactory.createComposite(node)

	val FILE: IFileElementType = IFileElementType(TomlLanguage)
	val COMMENTS: TokenSet = TokenSet.create(TomlElementTypes.COMMENT)
}


