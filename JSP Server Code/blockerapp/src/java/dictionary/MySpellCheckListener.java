/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary;
import java.util.List;
import com.swabunga.spell.event.SpellCheckEvent;
import com.swabunga.spell.event.SpellCheckListener;
/**
 *
 * @author 17mx22
 */
public class MySpellCheckListener implements SpellCheckListener {

	private List<String> misspelledWords;
	
	public MySpellCheckListener(List<String> misspelledWords) {

		this.misspelledWords = misspelledWords;
	}

	@Override
	public void spellingError(SpellCheckEvent event) {

		event.ignoreWord(true);
		misspelledWords.add(event.getInvalidWord());
	}

}