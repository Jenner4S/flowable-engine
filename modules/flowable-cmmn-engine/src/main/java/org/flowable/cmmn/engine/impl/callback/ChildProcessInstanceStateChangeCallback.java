/* Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.flowable.cmmn.engine.impl.callback;

import org.flowable.cmmn.engine.CmmnEngineConfiguration;
import org.flowable.engine.common.impl.callback.CallbackData;
import org.flowable.engine.common.impl.callback.RuntimeInstanceStateChangeCallback;

/**
 * @author Joram Barrez
 */
public class ChildProcessInstanceStateChangeCallback implements RuntimeInstanceStateChangeCallback {

    protected CmmnEngineConfiguration cmmnEngineConfiguration;
    
    public ChildProcessInstanceStateChangeCallback(CmmnEngineConfiguration cmmnEngineConfiguration) {
        this.cmmnEngineConfiguration = cmmnEngineConfiguration;
    }
    
    @Override
    public void stateChanged(CallbackData callbackData) {
        if ("completed".equals(callbackData.getNewState()) || "cancelled".equals(callbackData.getNewState())) {
            cmmnEngineConfiguration.getCmmnRuntimeService().triggerPlanItemInstance(callbackData.getCallbackId());
        }
    }
    
}
