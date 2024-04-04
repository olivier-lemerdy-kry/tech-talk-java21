package se.kry.demo5.codesnippets;

// Execute javadoc src/main/java/se/kry/demo5/codesnippets/CodeSnippets.java -d javadoc --snippet-path=./src/main/java/se/kry/demo5/codesnippets/snippet-files
public class CodeSnippets {

    /**
     * This is an example in Java 17.
     * <pre>{@code
     *    if (success) {
     *        System.out.println("This is a success!");
     *    } else {
     *        System.out.println("This is a failure");
     *    }
     * }
     * </pre>
     *
     * @param success a success flag
     */
    public void java17Style(boolean success) {
        if (success) {
            System.out.println("This is a success!");
        } else {
            System.out.println("This is a failure");
        }
    }

    /**
     * This is an example for inline snippets
     * {@snippet :
     *    if (success) {
     *        System.out.println("This is a success!");
     *    } else {
     *        System.out.println("This is a failure");
     *    }
     *}
     *
     * @param success a success flag
     */
    public void java21InlineSnippets(boolean success) {
        if (success) {
            System.out.println("This is a success!");
        } else {
            System.out.println("This is a failure");
        }
    }

    /**
     * This is an example for external snippets.
     * {@snippet class="ExternalCodeSnippets" region="java21ExternalSnippets" }"
     *
     * @param success a success flag
     */
    public void java21ExternalSnippets(boolean success) {
        if (success) {
            System.out.println("This is a success!");
        } else {
            System.out.println("This is a failure");
        }
    }

    /**
     * This is an example for highlighting.
     * {@snippet class="ExternalCodeSnippets" region="java21ExternalSnippetsWithHighlight" }"
     *
     * @param success a success flag
     */
    public void java21ExternalSnippetsWithHighlight(boolean success) {
        if (success) {
            System.out.println("This is a success!");
        } else {
            System.out.println("This is a failure");
        }
    }
}
