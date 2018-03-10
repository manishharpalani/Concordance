Notes:
There are some differences in the concordance produced from the PDF concordance ouput.
1. The example did not have seem to correct line numbers. Upon closer study this is
    likely due to the formatting of the paragraph in question. The paragram has 8
    lines in the PDF, however the last paragraph word ('marks') is cited on line 3.

2. Another differences in my concordance results from the sample output: 'e.g' vs 'e.g.'.
   I believe it is a minor difference. My LineParser logic strips out punctuation at start
   and end of each parsed word.

Complexity Order

Worst case complexity is O(N*log(N)) where N is the total number of words, and each word in the
text is unique. Array.sort is performed on the wordMap keys to create a alphabetic ordered Concordance.
     * Array.sort has worst cast O(N*logN)performance.

On the other generally unique words will be smaller than the number of total words. Let's assume unique
words U = 0.5*N. For this case Sort Complexity will be: 0.5Nlog(0.5N)
