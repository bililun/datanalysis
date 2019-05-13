# datanalysis: a visual analysis of the words in songs

This program visualizes the relationship between words in songs in an aesthetically pleasing way. 

## bureaucratic details

Written for AT Computer Science 2018-19 taught by Ann Greyson.

## understanding the visualization

Songs are experienced through sound and time, so by presenting lyrics as a static visual image, it's a whole new way to appreciate music. Each word is arranged alphabetically in a circle and linked to the words following it in the song. The connecting lines are darker to indicate close-together words and thicker to indicate a higher frequency of that pairing. Photos of a few select visualizations are available [here](/photos).

## getting started

[Processing 3](processing.org) was used to visualize this project. If you've downloaded Processing and would like to add another song to visualize, add a raw .txt file containing the lyrics to the [/data] folder, and change the file reference on line 30 of [/src/DataAnalysisMain] to the file you've added. If you want to save a photo of the visualization, change the location to save it on line 59. Once the program is running, press the space bar to save the photo.

## notes on the songs and photos included

A few songs are included in the [/data] folder, and some have photos of the visualization in the [/photos] folder:

Old Town Road by Lil Nas X: My favorite aesthetically. [Here's the photo.](/photos/oldtownroad.png)

Rap God by Eminem: Warning, this song has so many words. In order to fit them all, the canvas must be adjusted to 8000 x 8000 pixels, and it seriously slowed down my computer. 

Sugar, Sugar by The Archies: An incredibly repetitive song.

Girls/Girls/Boys by Panic! at the Disco and Roxanne by The Police are also included but I don't have any notes on them.
