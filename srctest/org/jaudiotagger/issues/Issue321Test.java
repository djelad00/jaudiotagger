package org.jaudiotagger.issues;

import org.jaudiotagger.AbstractTestCase;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Issue321.
 */
public class Issue321Test extends AbstractTestCase {

    public void testReadingMP4WithExtraByte() throws Exception
    {
        File orig = new File("testdata", "test.m4a");
        if (!orig.isFile())
        {
            System.err.println("Unable to test file - not available");
            return;
        }
        File testFile = AbstractTestCase.copyAudioToTmp("test.m4a");

        // append a byte
        final FileOutputStream out = new FileOutputStream(testFile, true);
        out.write(-64);
        out.close();

        AudioFile af = AudioFileIO.read(testFile);
        assertNotNull(af.getTag());
        System.out.println(af.getTag());
    }
}
