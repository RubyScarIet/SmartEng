import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import model.Letter;

public class UC04_AlphabetTest {

    private Letter letter;

    @Before
    public void setUp() {
        letter = new Letter("A", "vowel", "audio/a.mp3", "Apple", "audio/apple.mp3");
    }

    @Test
    public void testConstructorWithParams() {
        assertEquals("A", letter.getCharacter());
        assertEquals("vowel", letter.getType());
        assertEquals("audio/a.mp3", letter.getAudioURL());
        assertEquals("Apple", letter.getExampleWord());
        assertEquals("audio/apple.mp3", letter.getExampleAudioURL());
    }

    @Test
    public void testTypeIsConsonant() {
        Letter consonant = new Letter("B", "consonant", "audio/b.mp3", "Book", "audio/book.mp3");
        assertEquals("consonant", consonant.getType());
    }

    @Test
    public void testAudioURLContainsMp3Extension() {
        String url = letter.getAudioURL();
        assertTrue("AudioURL phải có đuôi .mp3", url.endsWith(".mp3"));
    }
}
