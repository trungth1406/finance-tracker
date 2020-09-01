package com.self.learn.caching.state;

/**
 *
 */
public abstract class Modification {


    public Modification(int lineNumer, String content) {
        this.lineNumer = lineNumer;
        this.content = content;
    }

    /**
     *
     */
    protected int lineNumer;

    /**
     *
     */
    protected String content;

    /**
     *
     */
    protected abstract Modification processContent();

    /**
     * @param fromCol
     * @return
     */
    public String getRange(char fromCol) {
        String[] contentArray = getContent();
        int length = contentArray.length;
        int charToInteger = fromCol;
        int nextPos = charToInteger + length - 1;
        char nextCol = (char) (nextPos);
        return String.format("%s%d:%s%d", fromCol, this.lineNumer, nextCol, this.lineNumer + length);
    }

    /**
     * NOTE: This method is not completed since it still need to process its content
     *
     * @return
     */
    public String[] getContent() {
        String[] contentArr = new String[4];
        if (isDate(this.content)) {
            contentArr[0] = this.content;
            for (int i = 1; i < 4; i++) {
                contentArr[i] = "";
            }
        } else {
            int lastNotNumer = 0;
            while (!Character.isDigit(this.content.charAt(lastNotNumer++))) ;
            contentArr[0] = "";
            contentArr[1] = content.substring(0, lastNotNumer - 1);
            contentArr[2] = "";
            contentArr[3] = content.substring(lastNotNumer - 1);
        }
        return contentArr;
    }

    private static boolean isDate(String content) {
        return content.contains("/") && Character.isDigit(content.charAt(0));
    }


}