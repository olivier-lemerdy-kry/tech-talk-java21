public class ExternalCodeSnippets {

    public void java21ExternalSnippets(boolean success) {
        // @start region=java21ExternalSnippets
        if (success) {
            System.out.println("This is a success!");
        } else {
            System.out.println("This is a failure");
        }
        // @end
    }

    public void java21ExternalSnippetsWithHighlight(boolean success) {
        // @start region=java21ExternalSnippetsWithHighlight
        if (success) {
            System.out.println("This is a success!"); // @highlight substring="println"
        } else {
            System.out.println("This is a failure");
        }
        // @end
    }

}