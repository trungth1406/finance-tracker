package com.self.learn.state;

/**
 *
 */
public class Create extends ModificationState {

    public Create(int lineNumer, String content) {
        super(lineNumer, content);
    }

    @Override
    public String[] getContent() {
        this.content = this.content.replace("+", " ");
        return super.getContent();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Create(this.lineNumer, this.content);
    }
}