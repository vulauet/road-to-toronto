package practice;

import org.junit.Assert;

import java.util.*;

public class Abbreviation {

    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a &&
                    b == pair.b;
        }

        @Override
        public int hashCode() {

            return Objects.hash(a, b);
        }
    }

    static String abbreviation(String a, String b) {
        Map<Pair, Boolean> resultCache = new HashMap<>();
        return abbreviable(a, b, 0, 0, resultCache) ? "YES" : "NO";
    }

    private static boolean abbreviable(
            String a,
            String b,
            int startA,
            int startB,
            Map<Pair, Boolean> resultCache
    ) {
        Pair pair = new Pair(startA, startB);
        if (!resultCache.containsKey(pair)) {
            if (a.length() - startA == 0 && b.length() - startB == 0)
                resultCache.put(pair, true);
            else if (b.length() - startB == 0) {
                resultCache.put(pair, true);
                for (int i = startA; i < a.length(); i++) {
                    if (a.charAt(i) <= 'Z') {
                        resultCache.put(pair, false);
                        break;
                    }
                }
            }
            else if (a.length() - startA < b.length() - startB)
                resultCache.put(pair, false);
            else if (a.charAt(startA) <= 'Z')
                resultCache.put(pair,
                        a.charAt(startA) == b.charAt(startB)
                                && abbreviable(a, b, startA + 1, startB + 1, resultCache));
            else if (a.charAt(startA) - 32 != b.charAt(startB))
                resultCache.put(pair, abbreviable(a, b, startA + 1, startB, resultCache));
            else resultCache.put(pair, abbreviable(a, b, startA + 1, startB, resultCache)
                        || abbreviable(a, b, startA + 1, startB + 1, resultCache));

        }
        return resultCache.get(pair);

    }


    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

//        System.out.print(abbreviation("AbcDE", "ABDE"));
        Assert.assertEquals("YES", abbreviation("daBcd", "ABC"));
        Assert.assertEquals("YES", abbreviation("AbcDE", "ABDE"));
        Assert.assertEquals("NO", abbreviation("AbcDE", "AFDE"));
        Assert.assertEquals("NO", abbreviation("KXzQ", "K"));
        Assert.assertEquals("NO",
                abbreviation("LLZOSYAMQRMBTZXTQMQcKGLR", "LLZOSYAMBTZXMQKLR"));
        Assert.assertEquals("YES",
                abbreviation(
                        "BFZZVHdQYHQEMNEFFRFJTQmNWHFVXRXlGTFNBqWQmyOWYWSTDSTMJRYHjBNTEWADLgHVgGIRGKFQSeCXNFNaIFAXOiQORUDROaNoJPXWZXIAABZKSZYFTDDTRGZXVZZNWNRHMvSTGEQCYAJSFvbqivjuqvuzafvwwifnrlcxgbjmigkms",
                        "BFZZVHQYHQEMNEFFRFJTQNWHFVXRXGTFNBWQOWYWSTDSTMJRYHBNTEWADLHVGIRGKFQSCXNFNIFAXOQORUDRONJPXWZXIAABZKSZYFTDDTRGZXVZZNWNRHMSTGEQCYAJSF"
                ));
        Assert.assertEquals("YES",
                abbreviation(
                        "hHhAhhcahhacaccacccahhchhcHcahaahhchhhchaachcaCchhchcaccccchhhcaahhhhcaacchccCaahhaahachhacaahhaachhhaaaCalhhchaccaAahHcchcazhachhhaaahaahhaacchAahccacahahhcHhccahaachAchahacaahcahacaahcahacaHhccccaahaahacaachcchhahhacchahhhaahcacacachhahchcaAhhcaahchHhhaacHcacahaccccaaahacCHhChchhhahhchcahaaCccccahhcaachhhacaaahcaaaccccaacaaHachaahcchaahhchhhcahahahhcaachhchacahhahahahAahaAcchahaahcaaaaahhChacahcacachacahcchHcaahchhcahaachnachhhhcachchahhhacHhCcaHhhhcaCccccaaahcahacahchahcaachcchaachahhhhhhhhcahhacacCcchahccaaaaaHhhccaAaaaCchahhccaahhacaccchhcahhcahaahhgacahcahhchcaaAccchahhhaahhccaaHcchaccacahHahChachhcaaacAhacacaacacchhchchacchchcacchachacaahachccchhhaccahcacchaccaahaaaccccccaaaaaaaHhcahcchmcHchcchaaahaccchaaachchHahcaccaaccahcacacahAhaacaacaccaccaaacahhhcacAhaCchcaacCcccachhchchcchhchahchchahchchhchcacaachahhccacachaAhaaachchhchchchhaachahaahahachhaaaccacahhcacchhhaaachaaacAahhcachchachhhcacchacaaChCahhhccahChaachhcahacchanaaacchhhccacacchcahccchAcahacaaachhacchachccaaHacaacAhahcCh",
                        "HAHHCHAACCCAHCHHAHHAHCACCHCCHHCAAHHCACCCAHHHACAAHHHHCHHCAHHAHHAAAHAACAAHAHHCAHAHACHACHCHACACHAAHHAAAHCAHHACACAACHHHCHAHCAHCHHHAHAHACCAAAHCHHCHHCCAACCCCAACHACAACAAHACHCHAHHACCHCAHHHAAACHACAACHCACACAHHCCHAHACCCACCAACHCHHHCCCCCHCCAHHCAAHHAHHHHHHHAACCCCAHCCAAAAAHHHAAAACCAHHCAHACACCHHCHAHAHHCHAACHHHHHCCHCCAHAHCHCAAACCACCCCHACCACHHACHHACACHACCAACCCCAAAAHHAHCHHHCCAHCCHACHHAHCCACACCHAHAAACACCCCAHCCAHACCCCCCHCCHHCHHHHCHCHCAHHHACHAHAACCCAAAACHAACAAAHHAAHAAAHACHHCACHCCHCHAACHACACHHCCCCCAHCACHAAAHCHCAHACAAC"
                ));

//        int q = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int qItr = 0; qItr < q; qItr++) {
//            String a = scanner.nextLine();
//
//            String b = scanner.nextLine();
//
//            String result = abbreviation(a, b);
//            System.out.print(result);
//        }
//
//        scanner.close();
    }
}
