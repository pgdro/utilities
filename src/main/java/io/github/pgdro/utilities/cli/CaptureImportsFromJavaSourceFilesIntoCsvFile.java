package io.github.pgdro.utilities.cli;

import io.github.pgdro.tools.core.CommonInteractiveClass;
import io.github.pgdro.tools.core.FileContentClass;
import java.nio.file.Path;
import picocli.CommandLine;
import picocli.CommandLine.Mixin;

/**
 * clean files older than a given number of days
 */
@CommandLine.Command(name = "CaptureImportsFromJavaSourceFilesIntoCsvFile",
                     description = "Get import inventory from all Java source files within a given folder")
class CaptureImportsFromJavaSourceFilesIntoCsvFile implements Runnable {

    /**
     * adds the options defined in
     * CommonInteractiveClass.FolderNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.FolderNameOptionMixinClass optFolderNames
            = new CommonInteractiveClass.FolderNameOptionMixinClass();
    /**
     * adds the options defined in
     * CommonInteractiveClass.OutFileNameOptionMixinClass to this command
     */
    @Mixin
    private final CommonInteractiveClass.OutFileNameOptionMixinClass optionOut =
            new CommonInteractiveClass.OutFileNameOptionMixinClass();

    @Override
    public void run() {
        final String[] inFolders = optFolderNames.getFolderNames();
        final String outCsvFile = optionOut.getOutFileName();
        for (final String strFolder : inFolders) {
            FileContentClass.ContentReadingSubClass.extractImportStatementsFromJavaSourceFilesIntoCsvFile(
                    Path.of(strFolder),
                    Path.of(outCsvFile));
        }
    }

    /**
     * Constructor
     */
    protected CaptureImportsFromJavaSourceFilesIntoCsvFile() {
        // intentionally left blank
    }

}
