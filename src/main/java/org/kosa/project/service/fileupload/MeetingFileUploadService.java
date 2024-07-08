package org.kosa.project.service.fileupload;

import org.springframework.stereotype.Service;

@Service
public class MeetingFileUploadService extends FileUploadService {
    @Override
    public String getTargetUrl() {
        return "meeting";
    }
}
