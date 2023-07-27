
package org.spiderflow.core.expression;

import java.io.OutputStream;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.spiderflow.core.expression.interpreter.AstInterpreter;
import org.spiderflow.core.expression.parsing.Ast;
import org.spiderflow.core.expression.parsing.Ast.Node;
import org.spiderflow.core.expression.parsing.Parser;


/**
 * A template is loaded by a {@link TemplateLoader} from a file marked up with the basis-template language. The template can be
 * rendered to a {@link String} or {@link OutputStream} by calling one of the <code>render()</code> methods. The
 * {@link ExpressionTemplateContext} passed to the <code>render()</code> methods is used to look up variable values referenced in the
 * template.
 */
@Slf4j
public class ExpressionTemplate {
    private final List<Node> nodes;

    /**
     * Internal. Created by {@link Parser}.
     **/
    private ExpressionTemplate(List<Node> nodes) {
        this.nodes = nodes;
    }

    public static ExpressionTemplate create(String source) {
        List<Node> nodes = Parser.parse(source);
        //log.info("Parse source:{},node result:{}", source, nodes);
        return new ExpressionTemplate(nodes);
    }

    /**
     * Internal. The AST nodes representing this template after parsing. See {@link Ast}. Used by {@link AstInterpreter}.
     **/
    public List<Node> getNodes() {
        return nodes;
    }

    /**
     * Renders the template using the TemplateContext to resolve variable values referenced in the template.
     **/
    public Object render(ExpressionTemplateContext context) {
        return AstInterpreter.interpret(this, context);
    }
}
