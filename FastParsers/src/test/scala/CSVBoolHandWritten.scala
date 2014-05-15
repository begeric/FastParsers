object CSVBoolHandWritten {
	
	def apply(input: Array[Char]): List[Boolean] = {
		var pos = 0
		val inputsize = input.length
		val values = new scala.collection.mutable.ListBuffer[Boolean]()
		var result1 = false
		var result2 = false
		var result3 = false
		
		var success = false
		if (pos < inputsize && input(pos) == '['){
			pos += 1;
			success = true
			while (success) {
				val tmp_pos = pos
				if (input(pos) == 't'){
					pos += 1
					success = true
				}
				else 
					success = false
				if (success) {
					if (input(pos) == 'r'){
						pos += 1
						success = true
					}
					else 
						success = false
						
					if (success) {
						if (input(pos) == 'u'){
							pos += 1
							success = true
						}
						else 
							success = false
							
						if (success) {
							if (input(pos) == 'e'){
								pos += 1
								success = true
							}
							else 
								success = false
							if (success)
								result1 = true
						}
					}
				}
				if (!success) {
					pos = tmp_pos
					if (input(pos) == 'f'){
						pos += 1
						success = true
					}
					else 
						success = false
					if (success) {
						if (input(pos) == 'a'){
							pos += 1
							success = true
						}
						else 
							success = false
							
						if (success) {
							if (input(pos) == 'l'){
								pos += 1
								success = true
							}
							else 
								success = false
								
							if (success) {
								if (input(pos) == 's'){
									pos += 1
									success = true
								}
								else 
									success = false
								if (success) {
									if (input(pos) == 'e'){
										pos += 1
										success = true
									}
									else 
										success = false
									if (success)
										result2 = false
								}
							}
						}
					}
					result3 = result2
				}
				else 
					result3 = result1
				if (success){
					values += (result3)
					if (input(pos) == ',')
						pos += 1
					else
						success = false
				}
			}
			if (pos < inputsize && input(pos) == ']')
				values.toList
			else
				Nil
		}
		else
			Nil
	}
}