// This is a generated file. Not intended for manual editing.
package org.toml.devkt.lang.parse;

import org.jetbrains.kotlin.com.intellij.lang.ASTNode;
import org.jetbrains.kotlin.com.intellij.lang.LightPsiParser;
import org.jetbrains.kotlin.com.intellij.lang.PsiBuilder;
import org.jetbrains.kotlin.com.intellij.lang.PsiBuilder.Marker;
import org.jetbrains.kotlin.com.intellij.lang.PsiParser;
import org.jetbrains.kotlin.com.intellij.psi.tree.IElementType;

import static org.toml.devkt.lang.parse.TomlParserUtil.*;
import static org.toml.devkt.lang.psi.TomlElementTypes.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class TomlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ARRAY) {
      r = Array(b, 0);
    }
    else if (t == ARRAY_TABLE) {
      r = ArrayTable(b, 0);
    }
    else if (t == INLINE_TABLE) {
      r = InlineTable(b, 0);
    }
    else if (t == KEY) {
      r = Key(b, 0);
    }
    else if (t == KEY_VALUE) {
      r = KeyValue(b, 0);
    }
    else if (t == LITERAL) {
      r = Literal(b, 0);
    }
    else if (t == TABLE) {
      r = Table(b, 0);
    }
    else if (t == TABLE_HEADER) {
      r = TableHeader(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return File(b, l + 1);
  }

  /* ********************************************************** */
  // '[' ArrayElement* ']'
  public static boolean Array(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARRAY, null);
    r = consumeToken(b, L_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, Array_1(b, l + 1));
    r = p && consumeToken(b, R_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ArrayElement*
  private static boolean Array_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Array_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ArrayElement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Array_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // Value (','|&']')
  static boolean ArrayElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Value(b, l + 1);
    p = r; // pin = 1
    r = r && ArrayElement_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ','|&']'
  private static boolean ArrayElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = ArrayElement_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &']'
  private static boolean ArrayElement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayElement_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, R_BRACKET);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ArrayTableHeader KeyValue*
  public static boolean ArrayTable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTable")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ArrayTableHeader(b, l + 1);
    r = r && ArrayTable_1(b, l + 1);
    exit_section_(b, m, ARRAY_TABLE, r);
    return r;
  }

  // KeyValue*
  private static boolean ArrayTable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTable_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!KeyValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrayTable_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // '[''[' Key ('.' Key)* ']'']'
  public static boolean ArrayTableHeader(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTableHeader")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TABLE_HEADER, null);
    r = consumeTokens(b, 2, L_BRACKET, L_BRACKET);
    p = r; // pin = 2
    r = r && report_error_(b, Key(b, l + 1));
    r = p && report_error_(b, ArrayTableHeader_3(b, l + 1)) && r;
    r = p && report_error_(b, consumeTokens(b, -1, R_BRACKET, R_BRACKET)) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('.' Key)*
  private static boolean ArrayTableHeader_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTableHeader_3")) return false;
    int c = current_position_(b);
    while (true) {
      if (!ArrayTableHeader_3_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArrayTableHeader_3", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '.' Key
  private static boolean ArrayTableHeader_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArrayTableHeader_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Key(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BARE_KEY
  //   | <<remap 'BARE_KEY_OR_NUMBER' 'BARE_KEY'>>
  //   | <<remap 'BARE_KEY_OR_DATE' 'BARE_KEY'>>
  static boolean BareKey(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BareKey")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BARE_KEY);
    if (!r) r = remap(b, l + 1, BARE_KEY_OR_NUMBER, BARE_KEY);
    if (!r) r = remap(b, l + 1, BARE_KEY_OR_DATE, BARE_KEY);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // <<remap 'BARE_KEY_OR_DATE' 'DATE_TIME'>> | DATE_TIME
  static boolean Date(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Date")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = remap(b, l + 1, BARE_KEY_OR_DATE, DATE_TIME);
    if (!r) r = consumeToken(b, DATE_TIME);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // FileForm*
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    int c = current_position_(b);
    while (true) {
      if (!FileForm(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // KeyValue | ArrayTable | Table
  static boolean FileForm(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FileForm")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = KeyValue(b, l + 1);
    if (!r) r = ArrayTable(b, l + 1);
    if (!r) r = Table(b, l + 1);
    exit_section_(b, l, m, r, false, FileForm_recover_parser_);
    return r;
  }

  /* ********************************************************** */
  // !(
  //   BARE_KEY | BARE_KEY_OR_NUMBER | BARE_KEY_OR_DATE | BASIC_STRING | LITERAL_STRING | '['
  // )
  static boolean FileForm_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FileForm_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !FileForm_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BARE_KEY | BARE_KEY_OR_NUMBER | BARE_KEY_OR_DATE | BASIC_STRING | LITERAL_STRING | '['
  private static boolean FileForm_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FileForm_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BARE_KEY);
    if (!r) r = consumeToken(b, BARE_KEY_OR_NUMBER);
    if (!r) r = consumeToken(b, BARE_KEY_OR_DATE);
    if (!r) r = consumeToken(b, BASIC_STRING);
    if (!r) r = consumeToken(b, LITERAL_STRING);
    if (!r) r = consumeToken(b, L_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '{' InlineTableElement* '}'
  public static boolean InlineTable(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineTable")) return false;
    if (!nextTokenIs(b, L_CURLY)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INLINE_TABLE, null);
    r = consumeToken(b, L_CURLY);
    p = r; // pin = 1
    r = r && report_error_(b, InlineTable_1(b, l + 1));
    r = p && consumeToken(b, R_CURLY) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // InlineTableElement*
  private static boolean InlineTable_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineTable_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!InlineTableElement(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "InlineTable_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // KeyValue (','|&'}')
  static boolean InlineTableElement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineTableElement")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = KeyValue(b, l + 1);
    p = r; // pin = 1
    r = r && InlineTableElement_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ','|&'}'
  private static boolean InlineTableElement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineTableElement_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    if (!r) r = InlineTableElement_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // &'}'
  private static boolean InlineTableElement_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InlineTableElement_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _AND_);
    r = consumeToken(b, R_CURLY);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // BareKey | BASIC_STRING | LITERAL_STRING
  public static boolean Key(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Key")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, KEY, "<key>");
    r = BareKey(b, l + 1);
    if (!r) r = consumeToken(b, BASIC_STRING);
    if (!r) r = consumeToken(b, LITERAL_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Key '=' <<atSameLine Value>>
  public static boolean KeyValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "KeyValue")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, KEY_VALUE, "<key value>");
    r = Key(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, EQ));
    r = p && atSameLine(b, l + 1, Value_parser_) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Number | Date | BOOLEAN
  //   | BASIC_STRING | LITERAL_STRING
  //   | MULTILINE_BASIC_STRING | MULTILINE_LITERAL_STRING
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = Number(b, l + 1);
    if (!r) r = Date(b, l + 1);
    if (!r) r = consumeToken(b, BOOLEAN);
    if (!r) r = consumeToken(b, BASIC_STRING);
    if (!r) r = consumeToken(b, LITERAL_STRING);
    if (!r) r = consumeToken(b, MULTILINE_BASIC_STRING);
    if (!r) r = consumeToken(b, MULTILINE_LITERAL_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // <<remap 'BARE_KEY_OR_NUMBER' 'NUMBER'>> | NUMBER
  static boolean Number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = remap(b, l + 1, BARE_KEY_OR_NUMBER, NUMBER);
    if (!r) r = consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // TableHeader KeyValue*
  public static boolean Table(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Table")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = TableHeader(b, l + 1);
    r = r && Table_1(b, l + 1);
    exit_section_(b, m, TABLE, r);
    return r;
  }

  // KeyValue*
  private static boolean Table_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Table_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!KeyValue(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Table_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  /* ********************************************************** */
  // '[' Key ('.' Key)* ']'
  public static boolean TableHeader(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableHeader")) return false;
    if (!nextTokenIs(b, L_BRACKET)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, TABLE_HEADER, null);
    r = consumeToken(b, L_BRACKET);
    p = r; // pin = 1
    r = r && report_error_(b, Key(b, l + 1));
    r = p && report_error_(b, TableHeader_2(b, l + 1)) && r;
    r = p && consumeToken(b, R_BRACKET) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('.' Key)*
  private static boolean TableHeader_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableHeader_2")) return false;
    int c = current_position_(b);
    while (true) {
      if (!TableHeader_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "TableHeader_2", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '.' Key
  private static boolean TableHeader_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TableHeader_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && Key(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // Literal | Array | InlineTable | BARE_KEY
  static boolean Value(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Value")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Literal(b, l + 1);
    if (!r) r = Array(b, l + 1);
    if (!r) r = InlineTable(b, l + 1);
    if (!r) r = consumeToken(b, BARE_KEY);
    exit_section_(b, m, null, r);
    return r;
  }

  final static Parser FileForm_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return FileForm_recover(b, l + 1);
    }
  };
  final static Parser Value_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return Value(b, l + 1);
    }
  };
}
