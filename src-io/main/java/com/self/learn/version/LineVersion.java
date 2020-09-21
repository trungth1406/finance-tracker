package com.self.learn.version;

import com.self.learn.state.*;

import java.util.Arrays;

public class LineVersion {
    private String content;
    private int lineNumber;

    public LineVersion(String content, int lineNumber) {
        this.content = content;
        this.lineNumber = lineNumber;
    }

    public int getLineNumer() {
        return 0;
    }

    public String getContent() {
        return this.content;
    }

    public int length() {
        return this.content.length();
    }


    public com.self.learn.state.Modification diff(LineVersion that) {
        this.content = purgeContent(this.content);
        that.content = purgeContent(that.content);
        int[][] countTable = longestSubSequenceTable(this.content, that.content);
        StringBuilder sb = new StringBuilder();
        String mod = genDiff(sb, countTable, this.content, that.content, this.length(), that.length());
        System.out.println(mod);
        return getModification(that.content,mod, that.lineNumber);
    }

    private static com.self.learn.state.Modification getModification(String original , String modified, int lineNumber) {
        if (modified.contains("+") && modified.contains("-")) {
            return new Update(lineNumber, original);
        } else if (modified.contains("+")) {
            return new Create(lineNumber, original);
        } else if (modified.contains("-")) {
            return new Delete(lineNumber, original);
        }
        return new Unchanged(lineNumber, original);
    }

    private static String purgeContent(String content) {
        return content.replace("-", "");
    }

    private static String genDiff(StringBuilder sb, int[][] countTable, String s1, String s2, int s1Len, int s2Len) {
        if (s1Len > 0 && s2Len > 0 && s1.charAt(s1Len - 1) == s2.charAt(s2Len - 1)) {
            sb.append(s1.charAt(s1Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len - 1, s2Len - 1);
        } else if (s2Len > 0 && (s1Len == 0 || countTable[s1Len - 1][s2Len] >= countTable[s1Len][s2Len - 1])) {
            sb.append(" +" + s2.charAt(s2Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len, s2Len - 1);
        } else if (s1Len > 0 && (s2Len == 0 || countTable[s1Len][s2Len - 1] > countTable[s1Len - 1][s2Len])) {
            sb.append(" -" + s1.charAt(s1Len - 1));
            return genDiff(sb, countTable, s1, s2, s1Len - 1, s2Len);
        }
        return sb.toString();
    }


    private static int[][] longestSubSequenceTable(String s1, String s2) {
        int[][] counts = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0 || j == 0) {
                    counts[i][j] = 0;
                } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    counts[i][j] = 1 + counts[i - 1][j - 1];
                } else {
                    counts[i][j] = Math.max(counts[i - 1][j], counts[i][j - 1]);
                }
            }
        }
        System.out.println(Arrays.deepToString(counts));
        return counts;
    }
}
