/*
 * Copyright (c) 2017-2019, Draque Thompson, draquemail@gmail.com
 * All rights reserved.
 *
 * Licensed under: MIT Licence
 * See LICENSE.TXT included with this code to read the full license agreement.

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
package org.darisadesigns.polyglotlina.CustomControls;

import java.awt.Color;
import org.darisadesigns.polyglotlina.DictCore;
import java.awt.Font;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import org.darisadesigns.polyglotlina.PGTUtil;

/**
 *
 * @author DThompson
 */
public final class PTable extends JTable {
    private final DictCore core;
    private final PCellEditor disabledEd;
    private final PCellRenderer disabledRend;
    
    public PTable(DictCore _core) {
        core = _core;
        disabledEd = new PCellEditor(false, core);
        disabledRend = new PCellRenderer(false, core);
        disabledEd.setBackground(Color.gray);
        disabledRend.setBackground(Color.darkGray);
        
        if (core != null) {
            Font font = PGTUtil.MENU_FONT.deriveFont((float)core.getOptionsManager().getMenuFontSize());
            this.getTableHeader().setFont(font);
        }
    }
    
    @Override
    public String getToolTipText(MouseEvent e) {
        String tip = "";
        java.awt.Point p = e.getPoint();
        int rowIndex = rowAtPoint(p);
        int colIndex = columnAtPoint(p);
        
        try {
            Object target = this.getValueAt(rowIndex, colIndex);

            if (target != null) {
                try {
                    tip = core.getPronunciationMgr().getPronunciation(target.toString());
                } catch (Exception ex) {
                    // user error: do not log
                    // IOHandler.writeErrorLog(e);
                    tip = "MALFORMED PRONUNCIATION REGEX: " + ex.getLocalizedMessage();
                }
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            // do nothing. This happens if a non-value portion of the table is hovered over due to how objects are fetched
            // all other errors bubble beyond this point
            // IOHandler.writeErrorLog(e);
        }
        
        return tip.isEmpty() ? super.getToolTipText(e) : tip;
    }
    
    @Override
    public TableCellRenderer getCellRenderer(int row, int column) {
        TableCellRenderer ret = disabledRend;
        
        if (this.getValueAt(row, column) != null) {
            ret = super.getCellRenderer(row, column);
        }
        
        return ret;
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        TableCellEditor ret = cellEditor;
        
        if (this.getValueAt(row, column) != null) {
            ret = super.getCellEditor(row, column);
        }
        
        return ret;
    }
}
