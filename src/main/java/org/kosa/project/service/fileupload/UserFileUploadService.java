package org.kosa.project.service.fileupload;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// userFileUploadService
@Service
public class UserFileUploadService extends FileUploadService {
    @Override
    public String getTargetUrl() {
        return "users";
    }
}
