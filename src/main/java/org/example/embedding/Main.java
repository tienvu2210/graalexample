package org.example.embedding;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Set;

/**
 * A basic polyglot application that tries to exercise a simple hello world style program in all installed languages.
 */

public class Main {
    static String JS_CODE = """
        function myFun(param){
            console.log('hello '+param);
        };
        myFun("from js");

        var foo = {
            "id": 1 + javaInstance.id,
            "text": "2" + javaInstance.text,
            "arr": [1,2,3],
        };

        // read java from javascript
        console.log(javaInstance.banana());
        console.log(javaInstance.anotherClass().yay());
        console.log("foo is", JSON.stringify(foo));
        foo;
    """;

    public static void main(String[] args) {
        MyClass javaInstance = new MyClass();
        System.out.println("Hello Java!");
        System.out.println(javaInstance.id);
        try (Context context = Context.newBuilder().allowAllAccess(true).build()) {
            context.getBindings("js").putMember("javaInstance", javaInstance);
            
            // Read Javascript from Java
            Value result = context.eval("js", JS_CODE);
            assert result.hasMembers();

            int id = result.getMember("id").asInt();
            assert id == 5;

            String text = result.getMember("text").asString();
            assert text.equals("26");

            Value array = result.getMember("arr");
            assert array.hasArrayElements();
            assert array.getArraySize() == 3;
            assert array.getArrayElement(1).asInt() == 2;
        }
    }
}
