package com.self.learn.state;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 *
 */
public class Update extends ModificationState {

    public Update(int lineNumer, String content) {
        super(lineNumer, content);
    }


    @Override
    public String[] getContent() {
        ArrayDeque<String> queue = new ArrayDeque<>(Arrays.asList(this.content.split("")));
        StringBuilder sb = new StringBuilder();
        while(queue.size() > 0){
            String popped = queue.pop();
            if(!popped.contains("-") && !popped.isEmpty()){
                sb.append(popped.replace("+", ""));
            }
        }
        this.content = sb.toString();
        return super.getContent();
    }
}