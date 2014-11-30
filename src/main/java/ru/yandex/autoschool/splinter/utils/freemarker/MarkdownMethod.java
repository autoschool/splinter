package ru.yandex.autoschool.splinter.utils.freemarker;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import org.pegdown.PegDownProcessor;

import java.util.List;

/**
 * @author Etki {@literal <etki@etki.name>}
 * @version %I%, %G%
 * @since 1.0
 */
public class MarkdownMethod implements TemplateMethodModelEx {
    public static final PegDownProcessor PROCESSOR = new PegDownProcessor();
    public TemplateModel exec(List args) throws TemplateModelException {
        if (args.size() != 1) {
            throw new TemplateModelException("Markdown method accepts exactly one argument");
        }
        return new SimpleScalar(PROCESSOR.markdownToHtml(args.get(0).toString()));
    }
}
