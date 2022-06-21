public class UniqueWords {
    private BookReader book = new BookReader("WarAndPeace.txt");

    public UniqueWords() {
        //addUniqueWordsToLinkedList();
       // addUniqueWordsToBST();
        addUniqueWordsToAVL();
    }
    /*

    public void addUniqueWordsToLinkedList() {
        String str = book.getWords().first();
        MyLinkedList<String> list = new MyLinkedList<>();
        int Int = 0;
        //String temp = list.first();
        long time1 = System.currentTimeMillis();
        while (str != null) {
            if (!list.contains(str)) {
                if (Int == 0) {
                    list.addBefore(str);
                    list.first();
                    Int++;
                } else {
                    list.addAfter(str);
                    list.next();
                }
            }

            str = book.getWords().next();
        }
        long time2 = System.nanoTime();
    }
    public void addUniqueWordsToBST() {
        String word = book.words.first();
        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>();

        long time1 = System.nanoTime();
        //while loop
        while (word != null) {
            if(tree.find(word) == null)
                tree.add(word);

            word = book.words.next();
        }
        long time2 = System.nanoTime();


        time1 = System.nanoTime();
        tree.toString();
        time2 = System.nanoTime();
        System.out.println("Adding unique words to a binary search tree... in " + ((time2 - time1) / 1000000) + " milliseconds.");
        System.out.println(tree.size() + " unique words");
        System.out.println("The binary search tree had a height of " + tree.height() +
                " and made " + Math.abs(tree.comparisons) + " comparisons.");
        time1 = System.nanoTime();
        tree.toString();
        time2 = System.nanoTime();
        System.out.println("Traversing the binary search tree... in " + ((time2 - time1) / 1000000) + " milliseconds.");

    }

     */
        public void addUniqueWordsToAVL() {
        String word = book.words.first();
        MyBinarySearchTree<String> tree = new MyBinarySearchTree<>();

        long time1 = System.nanoTime();
        //while loop
        while (word != null) {
            if(tree.find(word) == null)
                tree.add(word);

            word = book.words.next();
        }
        long time2 = System.nanoTime();
            System.out.println("Adding unique words to an AVL binary search tree... in " + ((time2 - time1) / 1000000) + " milliseconds.");
            System.out.println(tree.size() + " unique words");
            System.out.println(tree.height() + " height");
            System.out.println(Math.abs(tree.comparisons) + " comparisons");
            System.out.println(tree.rotations + " rotations");
            time1 = System.nanoTime();
            tree.toString();
            time2 = System.nanoTime();
            System.out.println("Traversing the AVL... in " + ((time2 - time1) / 1000000) + " milliseconds.");
        }


//        time1 = System.nanoTime();
//        tree.toString();
//        time2 = System.nanoTime();
    }














































































