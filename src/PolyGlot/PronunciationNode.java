/*
 * Copyright (c) 2014, Draque Thompson, draquemail@gmail.com
 * All rights reserved.
 *
 * Licensed under:
 * Creative Commons Attribution-NonCommercial 4.0 International Public License
 * 
 * Please see the included LICENSE.TXT file for the full text of this license.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

// TODO: sometimes the last populated pronunciation will not fire off in autopopulate
// figure out why that is and correct it.
package PolyGlot;

/**
 *
 * @author draque
 */
public class PronunciationNode extends DictNode{
    private String pronunciation = "";
    
    public String getPronunciation() {
        return pronunciation;
    }
    
    public void setPronunciation(String _pronunciation) {
        pronunciation = _pronunciation;
    }
    
    public boolean equals(PronunciationNode test) {
        boolean ret = true;
        
        ret = ret & (id.equals(test.getId()));
        
        ret = ret & (value != null && test.getValue() != null && value.equals(test.getValue()));
        
        ret = ret & (pronunciation != null && test.getPronunciation() != null && pronunciation.equals(test.getPronunciation()));
        
        return ret;
    }

    @Override
    public void setEqual(DictNode _node) {
        PronunciationNode node = (PronunciationNode) _node;
        
        this.setPronunciation(node.getPronunciation());
        this.setValue(node.getValue());
        this.setId(node.getId());
    }
}
