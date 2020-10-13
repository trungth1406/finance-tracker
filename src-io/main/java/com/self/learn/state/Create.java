package com.self.learn.state;

/**
 *
 */
public class Create extends Modification {

    public Create(int lineNumer, String content) {
        super(lineNumer, content);
    }

    @Override
    public String[] getContent() {
        this.content = this.content.replace("+"," ");
        return super.getContent();
    }
}