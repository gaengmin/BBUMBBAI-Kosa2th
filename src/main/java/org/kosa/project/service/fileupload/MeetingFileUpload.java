package org.kosa.project.service.fileupload;

public class MeetingFileUpload extends FileUploadService {
    @Override
    public String getTargetUrl() {
        return "meeting";
    }
}
